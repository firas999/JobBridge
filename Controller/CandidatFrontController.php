<?php

namespace App\Controller;

use App\Entity\Candidat;
use App\Entity\Urlizer;
use App\Entity\User;
use App\Form\CandidatType;
use App\Repository\CandidatRepository;
use App\Security\verifierdroit;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\EncoderFactoryInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;


/**
 * @Route("/candidat")
 */
class CandidatFrontController extends AbstractController
{
    private EncoderFactoryInterface $encoder;
    private UserPasswordEncoderInterface $pwdEncoder;
    public function __construct(EncoderFactoryInterface $encoder,UserPasswordEncoderInterface $enc)
    {
        $this->encoder = $encoder;
        $this->pwdEncoder = $enc;
    }
    /**
     * @Route("/candidats", name="candidatfront_index",methods={"GET"})
     */
    public function index(CandidatRepository $candidatRepository): Response
  {
      return $this->render('candidat_front/index.html.twig', [
          'candidats' => $candidatRepository->findAll(),
      ]);}



    /**
     * @Route("/newcandidat", name="candidatfront_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $candidat = new Candidat();
        $salt = md5(microtime());
        $form = $this->createForm(CandidatType::class, $candidat);
        $form->handleRequest($request);
        $encoder = $this->encoder->getEncoder(User::class);
        if ($form->isSubmitted() && $form->isValid())
        { /** @var UploadedFile $uploadedFile */
            $uploadedFile = $form['imageFile']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/uploads';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFilename
            );
            $candidat->setImg($newFilename);
            $entityManager = $this->getDoctrine()->getManager();
            $encodedPassword=$encoder->encodePassword($candidat->getMotdepasse(),$salt);
            $candidat->setMotdepasse($this->pwdEncoder->encodePassword($candidat,$candidat->getMotdepasse()));
            $candidat->setRoles(['ROLE_CANDIDATE']);

            $entityManager->persist($candidat);
            $entityManager->flush();

            return $this->redirectToRoute('candidatfront_index');}


        return $this->render('candidat_front/new.html.twig', [
            'candidat' => $candidat,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/{id}/showcandidat", name="candidatfront_show", methods={"GET"})
     */
    public function show(Candidat $candidat): Response
    {
        return $this->render('candidat_front/show.html.twig', [
            'candidat' => $candidat,
        ]);
    }
    /**
     * @Route("/{id}/editcandidat", name="candidatfront_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Candidat $candidat): Response
    {
        $form = $this->createForm(CandidatType::class, $candidat);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /** @var UploadedFile $uploadedFile */
            $uploadedFile = $form['imageFile']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/uploads';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFilename
            );
            $candidat->setImg($newFilename);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('candidatfront_index');
        }

        return $this->render('candidat_front/edit.html.twig', [
            'candidat' => $candidat,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="candidatfront_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Candidat $candidat): Response
    {
        if ($this->isCsrfTokenValid('delete'.$candidat->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($candidat);
            $entityManager->flush();
        }

        return $this->redirectToRoute('candidatfront_index');
    }
    /**
     * @Route("/recherche", name="rechercheCandidat")
     */
    public function searchAction(Request $request)
    {

        $data = $request->request->get('search');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\Candidat e
    WHERE e.secteur    LIKE :data')
            ->setParameter('data', '%'.$data.'%');


        $candidats = $query->getResult();

        return $this->render('candidat_front/index.html.twig', array(
            'candidats' => $candidats));

    }
    /**
     * @Route("/tri", name="triCandidatFront")
     */
    public function TriAction(Request $request)
    {




        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\Candidat e
    ORDER BY e.tailleentreprise ASC');



        $candidats = $query->getResult();

        return $this->render('candidat_front/index.html.twig', array(
            'candidats' => $candidats));

    }


    /**
     * @Route("/trinom", name="triNomFront")
     */
    public function TriNom(Request $request)
    {




        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\Candidat e
    ORDER BY e.nom ASC');



        $candidats = $query->getResult();

        return $this->render('candidat_front/index.html.twig', array(
            'candidats' => $candidats));

    }



}
