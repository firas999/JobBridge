<?php

namespace App\Controller;

use Dompdf\Dompdf;
use App\Entity\OffreStage;
use App\Form\OffreStageType;
use App\Repository\OffreStageRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\Request;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Dompdf\Options;
/**
 * @Route("/offre/stage")
 */
class OffreStageController extends AbstractController
{

    /**
     * @Route("/", name="offre_stage_index", methods={"GET"})
     */
    public function index(OffreStageRepository $offreStageRepository, FlashyNotifier $flashy): Response
    {
        $flashy->success('Bonjour!', 'http://your-awesome-link.com');
        return $this->render('offre_stage/index.html.twig', [
            'offre_stages' => $offreStageRepository->findAll(),
        ]);
    }
    /**
        * @Route("/pdf", name="pdf", methods={"GET"})
        */
        public function pdf(OffreStageRepository $offreStageRepository): Response
        {
            // Configure Dompdf according to your needs
            $pdfOptions = new Options();
            $pdfOptions->set('defaultFont', 'Arial');
    // Instantiate Dompdf with our options
            $dompdf = new Dompdf($pdfOptions);
    // Retrieve the HTML generated in our twig file
            $html = $this->renderView('offre_stage/pdf.html.twig', [
                'offre_stages' => $offreStageRepository->findAll(),
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
     * @Route("/home", name="home", methods={"GET"})
     */
    public function Frontindex(OffreStageRepository $offreStageRepository): Response
    {
        return $this->render('/baseFront.html.twig');
    }
     /**
     * @Route("/Front2", name="front_offre_stage_index", methods={"GET"})
     */
    public function Frontindex2(Request $request, PaginatorInterface $paginator): Response
    {
        $repository=$this->getdoctrine()->getRepository(OffreStage :: class)->findAll();
        $offre_stages=$paginator->paginate(
         $repository,
         $request->query->getInt('page', 1),
         5
        );
        return $this->render('Front/OffreStage.html.twig', [
            'offre_stages' => $offre_stages,
        ]);
    }
  /**
     * @Route("/newss", name="offre_emploi_newss")
     */
    function chercher(OffreStageRepository $repository,Request $request)
    {
        $data=$request->get('AfficherClasse');
        $student=$repository->findBy(['Exigence'=>$data]);
        return $this->render('offre_stage/index.html.twig', [
            'offre_stages'=>$student

            ]);
    }


     /**
     * @Route("/stats", name="offre_stage_stats")
     */
    public function statistiques(OffreStageRepository $offreStageRepository)
    {
        $offreStage = $offreStageRepository->findAll();
        $PFE=$this->getdoctrine()->getRepository(OffreStage :: class)->countForStage('PFE');
        $Tech=$this->getdoctrine()->getRepository(OffreStage :: class)->countForStage('Technicien');
        $ouvrier=$this->getdoctrine()->getRepository(OffreStage :: class)->countForStage('Ouvrier');
        $arrayOFcolors=["red","blue","green"];
        $array=[$PFE,$Tech,$ouvrier];
        $arrayOFnames=["PFE","Technicien","Ouvrier"];
        return $this->render('offre_stage/stats.html.twig',[
            'array' => json_encode($array),
            'arrayOFnames' => json_encode($arrayOFnames),
            'arrayOFcolors' => json_encode($arrayOFcolors)
        ]);
    }
      /**
     * @Route("/stat", name="front_offre_stage_stats")
     */
    public function frontstatistiques(OffreStageRepository $offreStageRepository)
    {
        $offreStage = $offreStageRepository->findAll();
        $PFE=$this->getdoctrine()->getRepository(OffreStage :: class)->countForStage('PFE');
        $Tech=$this->getdoctrine()->getRepository(OffreStage :: class)->countForStage('Technicien');
        $ouvrier=$this->getdoctrine()->getRepository(OffreStage :: class)->countForStage('Ouvrier');
        $array=[$PFE,$Tech,$ouvrier];
        $arrayOFnames=["PFE","Technicien","Ouvrier"];
        return $this->render('Front/stats.html.twig',[
            'array' => json_encode($array),
            'arrayOFnames' => json_encode($arrayOFnames)
        ]);
    }

    /**
     * @Route("/new", name="offre_stage_new", methods={"GET","POST"})
     */
    public function new(Request $request, FlashyNotifier $flashy): Response
    {
        $offreStage = new OffreStage();
        $form = $this->createForm(OffreStageType::class, $offreStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($offreStage);
            $entityManager->flush();
            return $this->redirectToRoute('offre_stage_index');
        }

        return $this->render('offre_stage/new.html.twig', [
            'offre_stage' => $offreStage,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="offre_stage_show", methods={"GET"})
     */
    public function show(OffreStage $offreStage): Response
    {
        return $this->render('offre_stage/show.html.twig', [
            'offre_stage' => $offreStage,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="offre_stage_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, OffreStage $offreStage): Response
    {
        $form = $this->createForm(OffreStageType::class, $offreStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
           
            return $this->redirectToRoute('offre_stage_index');
        }

        return $this->render('offre_stage/edit.html.twig', [
            'offre_stage' => $offreStage,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="offre_stage_delete", methods={"DELETE"})
     */
    public function delete(Request $request, OffreStage $offreStage, FlashyNotifier $flashy): Response
    {
        if ($this->isCsrfTokenValid('delete'.$offreStage->getId(), $request->request->get('_token'))) {
            $flashy->success('ok!', 'http://your-awesome-link.com');
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($offreStage);
            $entityManager->flush();
        }

        return $this->redirectToRoute('offre_stage_index');
    }
}
