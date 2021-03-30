<?php

namespace App\Controller;

use App\Entity\CandidatEmploi;
use App\Form\CandidatEmploiType;
use App\Repository\CandidatEmploiRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Dompdf\Dompdf;
use Dompdf\Options;

/**
 * @Route("/candidat/emploi")
 */
class CandidatEmploiController extends AbstractController
{
    /**
     * @Route("/", name="candidat_emploi_index", methods={"GET"})
     */
    public function index(CandidatEmploiRepository $candidatEmploiRepository): Response
    {
        return $this->render('candidat_emploi/index.html.twig', [
            'candidat_emplois' => $candidatEmploiRepository->findAll(),
        ]);
    }
    /**
     * @Route("/pdf", name="imprimer", methods={"GET"})
     */
    public function pdf(CandidatEmploiRepository $EntrepriseRepository): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
// Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
// Retrieve the HTML generated in our twig file
        $html = $this->renderView('candidat_emploi/pdf.html.twig', [
            'candidat_emplois' => $EntrepriseRepository->findAll(),
        ]);
// Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');
// Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
    }
    /**
     * @Route("/calendar", name="candidat_emploi_callendar")
     */
    public function calendar(CandidatEmploiRepository $calendar)
    {
        $events = $calendar->findAll();

        $rdvs = [];

        foreach($events as $event){
            $rdvs[] = [
                'id' => $event->getId(),
                'start' => $event->getDateCandiature()->format('Y-m-d H:i:s'),
                'end' => $event->getDateCandiature()->format('Y-m-d H:i:s'),
                'title' => $event->getemail(),

            ];
        }

        $data = json_encode($rdvs);

        return $this->render('candidat_emploi/calendar.html.twig', compact('data'));
    }

    /**
     * @Route("/2", name="candidat_emploi_index2", methods={"GET"})
     */
    public function indexA(CandidatEmploiRepository $offreEmploiRepository,FlashyNotifier $flashy): Response
    {
        $flashy->success('ajout effectuer avec succeés!', 'http://your-awesome-link.com');
        return $this->render('candidat_emploi/index.html.twig', [
            'candidat_emplois' => $offreEmploiRepository->findAll(),

        ]);
    }
    /**
     * @Route("/3", name="candidat_emploi_index3", methods={"GET"})
     */
    public function indexM(CandidatEmploiRepository $offreEmploiRepository,FlashyNotifier $flashy): Response
    {
        $flashy->success('modification effectuer avec succeés!', 'http://your-awesome-link.com');
        return $this->render('candidat_emploi/index.html.twig', [
            'candidat_emplois' => $offreEmploiRepository->findAll(),

        ]);
    }
    /**
     * @Route("/4", name="candidat_emploi_index4", methods={"GET"})
     */
    public function indexS(CandidatEmploiRepository $offreEmploiRepository,FlashyNotifier $flashy): Response
    {
        $flashy->success('suppression effectuer avec succeés!', 'http://your-awesome-link.com');
        return $this->render('candidat_emploi/index.html.twig', [
            'candidat_emplois' => $offreEmploiRepository->findAll(),

        ]);
    }
    /**
     * @Route("/new", name="candidat_emploi_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $candidatEmploi = new CandidatEmploi();
        $form = $this->createForm(CandidatEmploiType::class, $candidatEmploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($candidatEmploi);
            $entityManager->flush();

            return $this->redirectToRoute('candidat_emploi_index2');
        }

        return $this->render('candidat_emploi/new.html.twig', [
            'candidat_emploi' => $candidatEmploi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/news", name="candidat_emploi_news")
     */
    public function tri(CandidatEmploiRepository $offreEmploiRepository)
    {
        $CandidatEmploi=$this->getDoctrine()->getRepository(CandidatEmploi::class)->findByASC();
        return $this->render('candidat_emploi/index.html.twig', [
            'candidat_emplois' => $CandidatEmploi,
        ]);
    }


    /**
     * @Route("/{id}", name="candidat_emploi_show", methods={"GET"})
     */
    public function show(CandidatEmploi $candidatEmploi): Response
    {
        return $this->render('candidat_emploi/show.html.twig', [
            'candidat_emploi' => $candidatEmploi,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="candidat_emploi_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, CandidatEmploi $candidatEmploi): Response
    {
        $form = $this->createForm(CandidatEmploiType::class, $candidatEmploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('candidat_emploi_index3');
        }

        return $this->render('candidat_emploi/edit.html.twig', [
            'candidat_emploi' => $candidatEmploi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="candidat_emploi_delete", methods={"DELETE"})
     */
    public function delete(Request $request, CandidatEmploi $candidatEmploi): Response
    {
        if ($this->isCsrfTokenValid('delete'.$candidatEmploi->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($candidatEmploi);
            $entityManager->flush();
        }

        return $this->redirectToRoute('candidat_emploi_index4');
    }
}
